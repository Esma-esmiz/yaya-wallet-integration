import { Component } from '@angular/core';
import { MaterialModule } from '../../shared/Material.Module';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [MaterialModule,CommonModule, ReactiveFormsModule],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css',
})
export class SearchBarComponent {
  constructor(private formBuilder: FormBuilder) {}
  searchForm: FormGroup;
  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      search: [null],
    });
    console.log(this.searchForm.value);

  }

  submit() {
    if (!this.searchForm.valid) {
      return;
    }
    console.log(this.searchForm.value);
  }
}
