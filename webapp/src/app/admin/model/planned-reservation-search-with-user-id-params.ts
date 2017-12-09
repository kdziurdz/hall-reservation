import { PlannedReservationSearchParams } from '../../core/model/planned-reservation-search-params';

export interface PlannedReservationSearchWithUserIdParams extends PlannedReservationSearchParams {
  userId: number;
}
