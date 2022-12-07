import { Exam } from "./exam";
import { Student } from "./student";

export interface PassedExam{
  passedExamId?: number;
  exam?: Exam;
  student?: Student;
  grade: number;
}
