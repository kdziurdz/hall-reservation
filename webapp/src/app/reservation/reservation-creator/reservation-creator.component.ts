import {Component} from "@angular/core";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'hr-reservation-creator',
  templateUrl: './reservation-creator.component.html',
  styleUrls: ['./reservation-creator.component.scss']
})
export class ReservationCreatorComponent {


  constructor(private httpClient: HttpClient) {
  }

  doRequest() {
    this.httpClient.get('/api/hall').subscribe(response => {
      console.log('success', response);
    }, (err) => {
      console.log('err', err);
    })
  }
}
