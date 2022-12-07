import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpAuthService } from 'src/app/core/services/http-auth.service';
import { UserLoginDataService } from 'src/app/core/services/user-login-data.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm?: FormGroup;

  constructor(private fb: FormBuilder,
    private httpAuth: HttpAuthService,
    private userLoginData: UserLoginDataService,
    private router: Router) { }

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm(){
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin(){
    const loginData = this.loginForm?.getRawValue();

    if(loginData){
      this.httpAuth.login(loginData).subscribe( //zove login iz httpAuth servisa(observable) tamo su oni parametri iz header-a, uzima vrednosti i forme
       { next : userLoginData => {
          console.log('response', userLoginData);
          this.userLoginData.token = 'Basic ' + btoa(`${loginData?.username}:${loginData?.password}`);
          this.userLoginData.userLoginData = userLoginData; //prvi je injekcija servisa, zatim ide poziv setera koji dodeljuje vrednost!
          this.router.navigate(['/home']);
        }, error: error => {
          console.error('error: ', error.error);
          }
        }
      );
    }
  }
}
