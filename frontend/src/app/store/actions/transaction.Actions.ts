import { TransactionModel, Transactions } from '../transaction.model';
import { createAction, props } from "@ngrx/store";


export const LOAD_TRANSACTION_SUCCESS ="[transaction page] load transaction success";
export const LOAD_TRANSACTION = "[transaction page] load transaction";
export const loadtransaction = createAction(LOAD_TRANSACTION);
export const loadtransactionsuccess = createAction(LOAD_TRANSACTION_SUCCESS, props<{Transactions:TransactionModel[]}>());
