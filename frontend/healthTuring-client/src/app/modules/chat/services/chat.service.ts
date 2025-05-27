import { inject, Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { ChatMessage } from '../models/chat-message';
import { BehaviorSubject, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CHAT_SOCKET_ENDPOINT } from '../../../config';

@Injectable({providedIn: 'root'})
export class ChatService {

    private http = inject(HttpClient);

    private stompClient: any;
    private messageSubject: BehaviorSubject<ChatMessage[]> = new BehaviorSubject<ChatMessage[]>([]);

    constructor() {
        this.initConnenctionSocket();
    }

    initConnenctionSocket() {
        const url = '//localhost:8080/chat-socket';
        const socket = new SockJS(url);
        this.stompClient = Stomp.over(socket);
    }

    joinRoom(roomId: string) {
        this.stompClient.connect({}, () => {
            this.stompClient.subscribe(`/topic/${roomId}`, (messages: any) => {
                const messageContent = JSON.parse(messages.body);
                const currentMessage = this.messageSubject.getValue();
                currentMessage.push(messageContent);
                this.messageSubject.next(currentMessage);
            })
        })
        this.loadMessage(roomId);
    }

    sendMessage(roomId: string, chatMessage: ChatMessage) {
        this.stompClient.send(`/app/chat/${roomId}`, {}, JSON.stringify(chatMessage));
    }

    getMessageSubject() {
        return this.messageSubject.asObservable();
    }

    loadMessage(roomId: string): void {
        this.http.get<any[]>(CHAT_SOCKET_ENDPOINT(roomId)).pipe(
            map(result => {
                return result.map(res => {
                    return {
                        user: res.userName,
                        message: res.message,
                    } as ChatMessage;
                })
            })
        ).subscribe({
            next: (chatMessage: ChatMessage[]) => {
                this.messageSubject.next(chatMessage);
            },
            error: (error) => {
                console.error(error);
            }
        })
    }
    
}