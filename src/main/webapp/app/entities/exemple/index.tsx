import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Exemple from './exemple';
import ExempleDetail from './exemple-detail';
import ExempleUpdate from './exemple-update';
import ExempleDeleteDialog from './exemple-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ExempleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ExempleUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExempleDetail} />
      <ErrorBoundaryRoute path={match.url} component={Exemple} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ExempleDeleteDialog} />
  </>
);

export default Routes;
