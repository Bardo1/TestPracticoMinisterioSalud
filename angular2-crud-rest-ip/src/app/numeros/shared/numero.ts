import {Address} from './address';

export class numero {
  id: number;
  name: string;
  phone: string;
  email: string;
  address: Address = new Address();
}
