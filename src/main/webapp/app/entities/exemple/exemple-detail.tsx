import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './exemple.reducer';
import { IExemple } from 'app/shared/model/exemple.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IExempleDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ExempleDetail extends React.Component<IExempleDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { exempleEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterSampleApplicationApp.exemple.detail.title">Exemple</Translate> [<b>{exempleEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="typeString">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeString">Type String</Translate>
              </span>
            </dt>
            <dd>{exempleEntity.typeString}</dd>
            <dt>
              <span id="typeStringPattern">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeStringPattern">Type String Pattern</Translate>
              </span>
            </dt>
            <dd>{exempleEntity.typeStringPattern}</dd>
            <dt>
              <span id="typeInteger">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeInteger">Type Integer</Translate>
              </span>
            </dt>
            <dd>{exempleEntity.typeInteger}</dd>
            <dt>
              <span id="typeLong">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeLong">Type Long</Translate>
              </span>
            </dt>
            <dd>{exempleEntity.typeLong}</dd>
            <dt>
              <span id="typeBigDecimal">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeBigDecimal">Type Big Decimal</Translate>
              </span>
            </dt>
            <dd>{exempleEntity.typeBigDecimal}</dd>
            <dt>
              <span id="typeLocalDate">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeLocalDate">Type Local Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={exempleEntity.typeLocalDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="typeInstant">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeInstant">Type Instant</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={exempleEntity.typeInstant} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="typeImageBlob">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeImageBlob">Type Image Blob</Translate>
              </span>
            </dt>
            <dd>
              {exempleEntity.typeImageBlob ? (
                <div>
                  <a onClick={openFile(exempleEntity.typeImageBlobContentType, exempleEntity.typeImageBlob)}>
                    <img
                      src={`data:${exempleEntity.typeImageBlobContentType};base64,${exempleEntity.typeImageBlob}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                  <span>
                    {exempleEntity.typeImageBlobContentType}, {byteSize(exempleEntity.typeImageBlob)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="typeTextBlob">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeTextBlob">Type Text Blob</Translate>
              </span>
            </dt>
            <dd>{exempleEntity.typeTextBlob}</dd>
            <dt>
              <span id="typeAnyBlob">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeAnyBlob">Type Any Blob</Translate>
              </span>
            </dt>
            <dd>
              {exempleEntity.typeAnyBlob ? (
                <div>
                  <a onClick={openFile(exempleEntity.typeAnyBlobContentType, exempleEntity.typeAnyBlob)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {exempleEntity.typeAnyBlobContentType}, {byteSize(exempleEntity.typeAnyBlob)}
                  </span>
                </div>
              ) : null}
            </dd>
            <dt>
              <span id="typeBlob">
                <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeBlob">Type Blob</Translate>
              </span>
            </dt>
            <dd>
              {exempleEntity.typeBlob ? (
                <div>
                  <a onClick={openFile(exempleEntity.typeBlobContentType, exempleEntity.typeBlob)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                  <span>
                    {exempleEntity.typeBlobContentType}, {byteSize(exempleEntity.typeBlob)}
                  </span>
                </div>
              ) : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/exemple" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/exemple/${exempleEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ exemple }: IRootState) => ({
  exempleEntity: exemple.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExempleDetail);
