import { ReservationStatus } from '../../reservation/my-reservations/reservation-status.enum';

export interface PlannedReservationSearchParams {
  dateFrom: string;
  dateTo: string;
  hallIds: number[] | null;
  status: Array<ReservationStatus>;
  sort?: string;
  pageSize?: number;
  pageNumber?: number;
}
