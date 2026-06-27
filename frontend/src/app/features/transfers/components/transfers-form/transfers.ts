import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { TransferService } from '../../../../core/services/transfer-service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-transfers',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSnackBarModule,
    MatCardModule
  ],
  templateUrl: './transfers.html',
  styleUrl: './transfers.scss',
})
export class Transfers {
  
  @Output()
  transferCreated = new EventEmitter<void>();

  loading = false;
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private service: TransferService,
    private snackBar: MatSnackBar
  ) {
    this.form = this.fb.group({
      originAccount: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
      destinationAccount: ['', [Validators.required, Validators.maxLength(10), Validators.minLength(10)]],
      amount: [null, [Validators.required, Validators.min(0.01)]],
      transferDate: [null, Validators.required]
    });
  }

  submit() {

    this.loading = true;

    this.service.create(this.form.getRawValue() as any).subscribe({
      next: () => {
        this.loading = false;
        this.snackBar.open(
          'Transferência agendada com sucesso!',
          'Fechar',
          { duration: 3000 }
        );
        this.transferCreated.emit();
      },
      error: err => {
        this.loading = false;
        this.snackBar.open(
          err.error,
          'Fechar',
          { duration: 5000 }
        );
      }
    });

  }
}
