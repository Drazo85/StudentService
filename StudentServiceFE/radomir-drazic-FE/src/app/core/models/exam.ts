import { ExamTerm } from "./examTerm";
import { Student } from "./student";
import { Subject } from "./subject";

export interface Exam{
  id?: number;
  examTerm: ExamTerm;
  subject: Subject;
  date: Date;
  activeStudents?: Student[];
}
