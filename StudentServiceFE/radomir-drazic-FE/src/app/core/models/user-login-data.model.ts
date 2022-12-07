export interface UserLoginData{
  firstname: string;
  lastname: string;
  username: string;
  password: string;
  accountNotExpired: boolean;
  accountNotBlocked: boolean;
  credentialsNotExpired: boolean;
  enabled: boolean;
  authorities: [{authority: string}]
}
