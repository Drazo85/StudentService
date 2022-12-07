import { Exam } from "./exam";

export interface ExamTerm{
  examTermId?: number;
  name: string;
  startDate: Date;
  endDate: Date;
  isActive: boolean;
  exams?: Exam[];
}
