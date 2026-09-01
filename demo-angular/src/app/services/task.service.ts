import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";

export interface Task {
  id: number | null;
  title: string;
  completed: boolean;
}

@Injectable()
export class TaskService {
  private apiUrl = "http://localhost:8082/demo_springmvc/api/tasks";

  constructor(private http: HttpClient) {}

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.apiUrl);
  }

  createTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, task);
  }

  updateTask(task: Task): Observable<Task> {
    return this.http.put<Task>(this.apiUrl + "/" + task.id, task);
  }

  deleteTask(id: number): Observable<any> {
    return this.http.delete(this.apiUrl + "/" + id);
  }
}
