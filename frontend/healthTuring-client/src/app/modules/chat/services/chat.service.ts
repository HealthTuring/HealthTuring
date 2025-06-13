import { inject, Injectable } from '@angular/core';
import { Stomp, StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { ChatMessage } from '../models/chat-message';
import { BehaviorSubject, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { CHAT_SOCKET_ENDPOINT } from '../../../config';
import { environments } from '../../../../environments/environments';

@Injectable({ providedIn: 'root' })
export class ChatService {

  private http = inject(HttpClient);

  private stompClient: any;
  private messageSubject: BehaviorSubject<ChatMessage[]> = new BehaviorSubject<ChatMessage[]>([]);
  private isConnected: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public isConnected$ = this.isConnected.asObservable();

  private connectedPromise!: Promise<void>;
  private connectedResolver!: () => void;
  private currentSubscription: StompSubscription | null = null;

  constructor() {
    this.initConnectionSocket(); // Inicializamos conexión al crear el servicio
  }

  private initConnectionSocket() {
    const url = `${environments.baseUrl}chat-socket`;
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket);

    this.connectedPromise = new Promise(resolve => {
      this.connectedResolver = resolve;
    });

    this.stompClient.connect({}, () => {
      this.isConnected.next(true);
      this.connectedResolver(); // Marcamos como conectada
    });
  }

  async joinRoom(roomId: string) {
    await this.connectedPromise; // Esperamos a la conexión
    this.subscribeToRoom(roomId);
    this.loadMessage(roomId);
  }

  private subscribeToRoom(roomId: string) {
    this.leaveRoom(); // Limpiamos la suscripción anterior si existe
    this.currentSubscription = this.stompClient.subscribe(`/topic/${roomId}`, (messages: any) => {
      const messageContent = JSON.parse(messages.body);
      const currentMessage = this.messageSubject.getValue();
      currentMessage.push(messageContent);
      this.messageSubject.next(currentMessage);
    });
  }

  leaveRoom() {
    if (this.currentSubscription) {
      this.currentSubscription.unsubscribe();
      this.currentSubscription = null;
    }
  }

  sendMessage(roomId: string, chatMessage: ChatMessage) {
    this.stompClient.send(`/app/chat/${roomId}`, {}, JSON.stringify(chatMessage));
  }

  getMessageSubject() {
    return this.messageSubject.asObservable();
  }

  loadMessage(roomId: string): void {
    this.http.get<any[]>(CHAT_SOCKET_ENDPOINT(roomId)).pipe(
      map(result => result.map(res => ({
        user: res.userName,
        message: res.message,
      } as ChatMessage)))
    ).subscribe({
      next: (chatMessage: ChatMessage[]) => {
        this.messageSubject.next(chatMessage);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
}
