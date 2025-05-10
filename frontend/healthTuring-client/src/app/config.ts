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
