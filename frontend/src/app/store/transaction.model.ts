export interface TransactionModel{
    Transaction_ID: string,
    Sender: string,
    Receiver: string,
    Amount: number,
    Currency: string,
    Cause: string,
    Created_At: string
}

export interface Transactions{
    transactions:TransactionModel[]
}