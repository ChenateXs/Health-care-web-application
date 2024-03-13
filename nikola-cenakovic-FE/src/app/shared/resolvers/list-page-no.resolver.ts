import { Injectable } from '@angular/core';
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot,
  ActivatedRoute
} from '@angular/router';
import { HttpOrganizationService } from 'src/app/core/services/organization/http-organization.service';

@Injectable({
  providedIn: 'root'
})
export class ListPageNoResolver implements Resolve<number> {
  constructor(
    private activeRoute:ActivatedRoute){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): number {

    const pageNo = Number(this.activeRoute.snapshot.queryParams['pageNo']);
    return  pageNo? pageNo:1;
  }
}
