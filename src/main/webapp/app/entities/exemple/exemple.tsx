import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import {
  openFile,
  byteSize,
  Translate,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './exemple.reducer';
import { IExemple } from 'app/shared/model/exemple.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IExempleProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export type IExempleState = IPaginationBaseState;

export class Exemple extends React.Component<IExempleProps, IExempleState> {
  state: IExempleState = {
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
  }

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { exempleList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="exemple-heading">
          <Translate contentKey="jhipsterSampleApplicationApp.exemple.home.title">Exemples</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="jhipsterSampleApplicationApp.exemple.home.createLabel">Create new Exemple</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeString')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeString">Type String</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeStringPattern')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeStringPattern">Type String Pattern</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeInteger')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeInteger">Type Integer</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeLong')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeLong">Type Long</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeBigDecimal')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeBigDecimal">Type Big Decimal</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeLocalDate')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeLocalDate">Type Local Date</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeInstant')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeInstant">Type Instant</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeImageBlob')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeImageBlob">Type Image Blob</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeTextBlob')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeTextBlob">Type Text Blob</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeAnyBlob')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeAnyBlob">Type Any Blob</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('typeBlob')}>
                  <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeBlob">Type Blob</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {exempleList.map((exemple, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${exemple.id}`} color="link" size="sm">
                      {exemple.id}
                    </Button>
                  </td>
                  <td>{exemple.typeString}</td>
                  <td>{exemple.typeStringPattern}</td>
                  <td>{exemple.typeInteger}</td>
                  <td>{exemple.typeLong}</td>
                  <td>{exemple.typeBigDecimal}</td>
                  <td>
                    <TextFormat type="date" value={exemple.typeLocalDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={exemple.typeInstant} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    {exemple.typeImageBlob ? (
                      <div>
                        <a onClick={openFile(exemple.typeImageBlobContentType, exemple.typeImageBlob)}>
                          <img
                            src={`data:${exemple.typeImageBlobContentType};base64,${exemple.typeImageBlob}`}
                            style={{ maxHeight: '30px' }}
                          />
                          &nbsp;
                        </a>
                        <span>
                          {exemple.typeImageBlobContentType}, {byteSize(exemple.typeImageBlob)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{exemple.typeTextBlob}</td>
                  <td>
                    {exemple.typeAnyBlob ? (
                      <div>
                        <a onClick={openFile(exemple.typeAnyBlobContentType, exemple.typeAnyBlob)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                          &nbsp;
                        </a>
                        <span>
                          {exemple.typeAnyBlobContentType}, {byteSize(exemple.typeAnyBlob)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {exemple.typeBlob ? (
                      <div>
                        <a onClick={openFile(exemple.typeBlobContentType, exemple.typeBlob)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                          &nbsp;
                        </a>
                        <span>
                          {exemple.typeBlobContentType}, {byteSize(exemple.typeBlob)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${exemple.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${exemple.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${exemple.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ exemple }: IRootState) => ({
  exempleList: exemple.entities,
  totalItems: exemple.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Exemple);
