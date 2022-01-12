import {Injectable} from "@angular/core";
import {Subject} from "rxjs";
import {UsersDTO} from "../../tb/api";

@Injectable()
export class UserServiceNotification {
  dataChanged$: Subject<UsersDTO> = new Subject<UsersDTO>();
}
