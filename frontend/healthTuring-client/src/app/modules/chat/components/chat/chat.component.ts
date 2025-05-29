import { Component, inject, OnInit, Signal, effect, AfterViewChecked, ViewChild, ElementRef } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { ChatMessage } from '../../models/chat-message';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { JwtService } from '../../../../core/services/jwt.service';
import { PatientService } from '../../../../shared/services/patient.service';
import { SelectPatientComponent } from '../select-patient/select-patient.component';
import { PatientDto } from '../../../../shared/interfaces/patient-dto.interface';

@Component({
  selector: 'app-chat',
  imports: [FormsModule, CommonModule, SelectPatientComponent],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit, AfterViewChecked {

  @ViewChild('messagesContainer') private messagesContainer!: ElementRef;

  private chatService = inject(ChatService);
  private jwtService = inject(JwtService);
  private patientService = inject(PatientService);

  messageInput: string = '';
  userId: string = '';
  messageList: any[] = [];
  roomId: string = '';
  currentRoom: string | null = null;
  isDoctor = false;

  patientSignal: Signal<PatientDto | null> = this.patientService.getPatient();
  doctorName: string = 'Doctor';

  constructor() {

    effect(() => {
      const patient = this.patientSignal();

      console.log(patient)

      if (patient != null) {
        this.doctorName = patient.doctorName;
        const patientId = patient.id;
        const doctorId = patient.doctorId;
        let newRoomId: string;

        if (this.jwtService.getRole() === 'ROLE_DOC') {
          newRoomId = `room${patientId}${doctorId}`;
        } else {
          newRoomId = `room${patientId}${doctorId}`;
        }

        if (this.currentRoom && this.currentRoom !== newRoomId) {
          this.chatService.leaveRoom();
        }

        this.roomId = newRoomId;
        this.currentRoom = newRoomId;
        this.chatService.joinRoom(this.roomId);
      }
    });

  }

  ngOnInit(): void {
    if (this.jwtService.getRole() === 'ROLE_DOC') {
      this.isDoctor = true;
    }
    this.userId = this.jwtService.getId()?.toString() ?? '';
    this.lisenerMessage();
  }

  ngAfterViewChecked() {
    this.scrollToBottom();
  }

  sendMessage() {
    const chatMessage: ChatMessage = {
      message: this.messageInput,
      user: this.userId,
    };

    this.chatService.sendMessage(this.roomId, chatMessage);
    this.messageInput = '';
  }

  lisenerMessage() {
    this.chatService.getMessageSubject().subscribe((messages): any => {
      this.messageList = messages.map((item: any) => ({
        ...item,
        message_side: item.user === this.userId ? 'sender' : 'receiver',
      }));
    });
  }

  private scrollToBottom(): void {
    try {
      this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight;
    } catch (err) { }
  }

}
