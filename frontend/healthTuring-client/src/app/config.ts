import { environments } from "../environments/environments";

export const BASE_URL = environments.baseUrl;
export const API_URL = `${BASE_URL}api`;

/* AUTH ENDPOINTS */

export const LOGIN_ENDPOINT = `${API_URL}/auth/login`;
export const CHECK_STATUS_ENDPOINT = `${API_URL}/auth/check-status`;