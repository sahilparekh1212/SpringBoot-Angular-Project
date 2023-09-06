import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { AuthService } from 'src/app/services/authService/auth.service';

export const authGuardFn: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
): boolean => {

  let authService = inject(AuthService);
  let router = inject(Router);

  let canAccess: boolean = authService.getIsUserAuthnticated();

  if (!canAccess) {
    router.navigateByUrl("/login");
  }
  return canAccess;
};