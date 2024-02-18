import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { MaterialModule } from '../../shared/Material.Module';
import { Store } from '@ngrx/store';
import { MasterService } from '../../store/master.service';
import { TransactionModel } from '../../store/transaction.model';
@Component({
  selector: 'app-transaction-table',
  standalone: true,
  imports: [MaterialModule],
  templateUrl: './transaction-table.component.html',
  styleUrl: './transaction-table.component.css'
})
export class TransactionTableComponent implements AfterViewInit {
  displayedColumns: string[] = ['Transaction_ID', 'Sender', 'Receiver', 'Amount','Currency','Cause', "Created_At"];
  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(private apiService:MasterService){  }
  dataSource = new MatTableDataSource<TransactionModel[]>([]);

  ngOnInit() {
    this.apiService.getTransactionByAccount().subscribe(data=>{
            this.dataSource = new MatTableDataSource<TransactionModel[]>(data);
            // this.dataSource.paginator = this.paginator;
          console.warn(data)
    });
  };

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  
}

 

 
