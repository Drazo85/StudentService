import { City } from "./city";
import { Exam } from "./exam";

export interface Student{
  studentId?: number;
  indexNumber: string;
  indexYear: number;
  firstName: string;
  lastName: string;
  email: string;
  address?: string;
  city: City;
  yearOfStudy: number;
  passedExams?: Exam[];
  activeExams?: Exam[];
}
