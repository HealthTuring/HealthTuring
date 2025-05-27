import { Component, inject, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { ChatMessage } from '../../models/chat-message';
import { ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-chat',
  imports: [FormsModule, CommonModule],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  private chatService = inject(ChatService);
  private route = inject(ActivatedRoute);

  messageInput: string = '';
  userId: string = '';
  messageList: any[] = [];

  ngOnInit(): void {
    this.userId = this.route.snapshot.params['userId'];
    this.chatService.joinRoom('ABC');
    this.lisenerMessage();
  }

  sendMessage() {

    const chatMessage = {
      message: this.messageInput,
      user: this.userId,
    } as ChatMessage

    this.chatService.sendMessage('ABC', chatMessage);
    this.messageInput = '';

  }

  lisenerMessage() {
    this.chatService.getMessageSubject().subscribe((messages): any => {
      this.messageList = messages.map((item: any) => ({
        ...item,
        message_side: item.user === this.userId ? 'sender' : 'receiver',
      }));
    })
  }

}
