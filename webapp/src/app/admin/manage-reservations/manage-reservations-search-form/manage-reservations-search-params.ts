import { PlannedReservationSearchParams } from '../../../core/model/planned-reservation-search-params';

export interface ManageReservationsSearchParams extends PlannedReservationSearchParams {
  userIds: number[] | null;
}
