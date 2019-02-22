import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Exemple from './exemple';
import Region from './region';
import Country from './country';
import Location from './location';
import Department from './department';
import Task from './task';
import Employee from './employee';
import Job from './job';
import JobHistory from './job-history';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/exemple`} component={Exemple} />
      <ErrorBoundaryRoute path={`${match.url}/region`} component={Region} />
      <ErrorBoundaryRoute path={`${match.url}/country`} component={Country} />
      <ErrorBoundaryRoute path={`${match.url}/location`} component={Location} />
      <ErrorBoundaryRoute path={`${match.url}/department`} component={Department} />
      <ErrorBoundaryRoute path={`${match.url}/task`} component={Task} />
      <ErrorBoundaryRoute path={`${match.url}/employee`} component={Employee} />
      <ErrorBoundaryRoute path={`${match.url}/job`} component={Job} />
      <ErrorBoundaryRoute path={`${match.url}/job-history`} component={JobHistory} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
