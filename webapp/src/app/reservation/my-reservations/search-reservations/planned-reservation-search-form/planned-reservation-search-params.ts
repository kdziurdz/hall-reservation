import { ReservationStatus } from '../../reservation-status.enum';
export interface PlannedReservationSearchParams {
  dateFrom: string;
  dateTo: string;
  hallIds: number[] | null;
  status: Array<ReservationStatus>;
}
