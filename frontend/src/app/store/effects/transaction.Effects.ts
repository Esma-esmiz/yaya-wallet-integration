import { Injectable } from "@angular/core";
import { Actions, createEffect, ofType } from "@ngrx/effects";
import { MasterService } from "../master.service";
import { LOAD_TRANSACTION, loadtransactionsuccess } from "../actions/transaction.Actions";
import { EMPTY, catchError, exhaustMap, map } from "rxjs";




export class TransactionEffects{
    constructor(private action$:Actions, private service:MasterService){ }

    _transaction= createEffect(()=>
       this.action$.pipe( 
        ofType(LOAD_TRANSACTION),
        exhaustMap((action)=>{
            return this.service.getTransactionByAccount().pipe(
                map((data) =>{
                    return loadtransactionsuccess({Transactions:data});
                }),
                catchError(() => EMPTY)     
            )
            }))
            );
}