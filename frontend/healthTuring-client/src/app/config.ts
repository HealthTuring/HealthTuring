import { environments } from "../environments/environments";

export const BASE_URL = environments.baseUrl;
export const API_URL = `${BASE_URL}api`;

/* AUTH ENDPOINTS */
export const LOGIN_ENDPOINT = `${API_URL}/auth/login`;
export const REGISTER_ENDPOINT = `${API_URL}/auth/register`;
export const CHECK_STATUS_ENDPOINT = `${API_URL}/auth/check-status`;
export const FORGET_PASSWORD_ENDPOINT = `${API_URL}/auth/forget-password`;
export const RESET_PASSWORD_ENDPOINT = (token: string) => `${API_URL}/auth/reset-password/${token}`;
export const CONFIRM_EMAIL_ENDPOINT = `${API_URL}/auth/email-confirmation`;
export const LOGOUT_ENDPOINT = `${API_URL}/auth/logout`;

/* PATIENT ENDPOINTS */
export const PATIENTS_NAMES_BYUSER_ENDPOINT = (userId: number) => `${API_URL}/patient/user-patients/${userId}`;
export const PATIENTS_NAMES_BYDOCTOR_ENDPOINT = (doctorId: number) => `${API_URL}/doctor/user-patients-doctor/${doctorId}`;
export const DOCTOR_BY_PATIENT = (patientId: number) => `${API_URL}/patient/doctor/${patientId}`

/* TREATMENT ENDPOINTS */
export const TREATMENTS_BY_PATIENT_ENDPOINT = (patientId: number) => `${API_URL}/treatments/${patientId}`;

/* APPOINTMENT ENDPOINTS */
export const APPOINTMENTS_BY_PATIENT_ENDPOINT = (patientId: number) => `${API_URL}/appointments/${patientId}`;
export const SLOTS_ENDPOINT = (doctorId: number) => `${API_URL}/appointments/doctor/${doctorId}/available-slots`;
export const RESERVE_APPOINTMENT_ENDPOINT = `${API_URL}/appointments/reserve`;

/* CHAT ENDPOINTS */
export const CHAT_SOCKET_ENDPOINT = (roomId: string) => `${API_URL}/chat/${roomId}`;

/* MEDICAMENTS ENDPOINTS */
export const ALL_MEDICAMENTS_ENDPOINT = `${API_URL}/medicaments`;

/* DOCTOR ENDPOINTS */
export const EDIT_TREATMENTS_ENDPOINT = (id: number) => `${API_URL}/doctor/edit-treatment/${id}`;
export const CREATE_TREATMENTS_ENDPOINT = `${API_URL}/doctor/create-treatment`;
export const DELETE_TREATMENTS_ENDPOINT = (id: number) => `${API_URL}/doctor/delete-treatment/${id}`;
