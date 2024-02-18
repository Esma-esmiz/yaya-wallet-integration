import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionModel } from './transaction.model';
@Injectable({
  providedIn: 'root'
})
export class MasterService {
   jsonUrl:string="http://localhost:3000/transaction"; // from uri of local json server
   api:string = "http://localhost:8080/api/v1/transaction/search";
   body = { query: "kebede" }; // get account hollder from autheniticated user
  constructor(private http: HttpClient) {   }
  getTransactionByAccount(){
    return this.http.post<any>(this.api, this.body)
  }
  
}
