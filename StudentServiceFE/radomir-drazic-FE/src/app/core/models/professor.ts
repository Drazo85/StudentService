import { City } from "./city";
import { Title } from "./title";

export interface Professor{
  id?: number;
  firstName: string;
  lastName: string;
  email: string;
  address?: string;
  city: City;
  title: Title;
  phoneNumber: string;
  reelectionDate: Date;
}
