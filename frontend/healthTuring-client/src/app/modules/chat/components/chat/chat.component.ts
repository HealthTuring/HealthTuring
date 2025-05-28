import { Component, inject, OnInit, Signal, effect } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { ChatMessage } from '../../models/chat-message';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { JwtService } from '../../../../core/services/jwt.service';
import { PatientService } from '../../../../shared/services/patient.service';
import { SelectPatientComponent } from '../select-patient/select-patient.component';

@Component({
  selector: 'app-chat',
  imports: [FormsModule, CommonModule, SelectPatientComponent],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  private chatService = inject(ChatService);
  private jwtService = inject(JwtService);
  private patientService = inject(PatientService);

  messageInput: string = '';
  userId: string = '';
  messageList: any[] = [];
  roomId: string = '';
  currentRoom: string | null = null;
  isDoctor = false;

  patientIdSignal: Signal<number | null> = this.patientService.getPatientId();

  constructor() {
    // Esto se ejecuta automáticamente cuando cambia el patientId
    effect(() => {
      const patientId = this.patientIdSignal();
      if (patientId != null) {
        const newRoomId = `room${patientId}3`;

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
}
