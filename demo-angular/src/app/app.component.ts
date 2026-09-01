import { Component, OnInit } from "@angular/core";
import { Task, TaskService } from "./services/task.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent implements OnInit {
  tasks: Task[] = [];

  newTaskTitle: string = "";

  loading: boolean = true;

  editingTaskId: number | null = null;

  error: string = "";

  constructor(private taskService: TaskService) {}

  ngOnInit() {
    this.taskService.getTasks().subscribe(
      (tasks) => {
        this.tasks = tasks;
        this.loading = false;
      },
      (error) => {
        console.error("Erreur lors du chargement des tâches", error);
        this.loading = false;
        this.error = "Impossible de contacter le serveur.";
      },
    );
  }

  addTask() {
    if (!this.newTaskTitle.trim()) {
      return;
    }

    const task: Task = {
      id: null,
      title: this.newTaskTitle,
      completed: false,
    };

    this.taskService.createTask(task).subscribe(
      (createdTask) => {
        this.tasks.push(createdTask);

        this.newTaskTitle = "";
      },
      (error) => {
        console.error("Erreur lors de la création de la tâche", error);
      },
    );
  }

  toggleTask(task: Task) {
    task.completed = !task.completed;

    this.taskService.updateTask(task).subscribe(
      (updatedTask) => {
        console.log("Tâche mise à jour", updatedTask);
      },
      (error) => {
        console.error("Erreur lors de la mise à jour", error);

        // On annule la modification si le serveur échoue
        task.completed = !task.completed;
      },
    );
  }

  startEditing(task: Task) {
    this.editingTaskId = task.id;
  }

  cancelEditing() {
    this.editingTaskId = null;
  }

  saveTask(task: Task) {
    this.taskService.updateTask(task).subscribe(
      (updatedTask) => {
        const index = this.tasks.findIndex((t) => t.id === updatedTask.id);

        if (index !== -1) {
          this.tasks[index] = updatedTask;
        }

        this.editingTaskId = null;
      },
      (error) => {
        console.error("Erreur lors de la mise à jour", error);
      },
    );
  }

  deleteTask(task: Task) {
    if (task.id === null) {
      return;
    }

    if (!confirm("Voulez-vous vraiment supprimer cette tâche ?")) {
      return;
    }

    this.taskService.deleteTask(task.id).subscribe(
      () => {
        this.tasks = this.tasks.filter((t) => t.id !== task.id);
      },
      (error) => {
        console.error("Erreur lors de la suppression", error);
      },
    );
  }
}
