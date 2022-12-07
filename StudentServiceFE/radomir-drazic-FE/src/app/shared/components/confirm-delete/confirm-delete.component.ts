import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirm-delete',
  templateUrl: './confirm-delete.component.html',
  styleUrls: ['./confirm-delete.component.css']
})
export class ConfirmDeleteComponent implements OnInit {

  @Input() header = '';
  message = '';

  constructor(public modal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  onConfirm() {
    this.modal.close('OK');
  }

  onCancel(){
    this.modal.close('Cancel');
  }
}
