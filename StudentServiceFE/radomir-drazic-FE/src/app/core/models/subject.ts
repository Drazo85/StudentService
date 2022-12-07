import { Professor } from "./professor";

export interface Subject{
    subjectId?: number;
    name: string;
    description?: string;
    noOfESP: number;
    yearOfStudy: number;
    semester: string;
    professor: Professor;
}
