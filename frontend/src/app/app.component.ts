import { Component, isDevMode } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {MaterialModule} from './shared/Material.Module';
import { TransactionTableComponent } from './components/transaction-table/transaction-table.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { EffectsModule } from '@ngrx/effects';
import { TransactionEffects } from './store/effects/transaction.Effects';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule, 
    RouterOutlet,
    MaterialModule,
    TransactionTableComponent,
    SearchBarComponent,
    // StoreModule.forRoot(AppState),
    // StoreDevtoolsModule.instrument({maxAge:false, logOnly: !isDevMode}),
    // EffectsModule.forRoot([TransactionEffects])
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'yaya-client';
}
