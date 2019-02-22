import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './exemple.reducer';
import { IExemple } from 'app/shared/model/exemple.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IExempleUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IExempleUpdateState {
  isNew: boolean;
}

export class ExempleUpdate extends React.Component<IExempleUpdateProps, IExempleUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => this.props.setBlob(name, data, contentType), isAnImage);
  };

  clearBlob = name => () => {
    this.props.setBlob(name, undefined, undefined);
  };

  saveEntity = (event, errors, values) => {
    values.typeInstant = convertDateTimeToServer(values.typeInstant);

    if (errors.length === 0) {
      const { exempleEntity } = this.props;
      const entity = {
        ...exempleEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/exemple');
  };

  render() {
    const { exempleEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    const {
      typeImageBlob,
      typeImageBlobContentType,
      typeTextBlob,
      typeAnyBlob,
      typeAnyBlobContentType,
      typeBlob,
      typeBlobContentType
    } = exempleEntity;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="jhipsterSampleApplicationApp.exemple.home.createOrEditLabel">
              <Translate contentKey="jhipsterSampleApplicationApp.exemple.home.createOrEditLabel">Create or edit a Exemple</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : exempleEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="exemple-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="typeStringLabel" for="typeString">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeString">Type String</Translate>
                  </Label>
                  <AvField id="exemple-typeString" type="text" name="typeString" />
                </AvGroup>
                <AvGroup>
                  <Label id="typeStringPatternLabel" for="typeStringPattern">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeStringPattern">Type String Pattern</Translate>
                  </Label>
                  <AvField
                    id="exemple-typeStringPattern"
                    type="text"
                    name="typeStringPattern"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      minLength: { value: 1, errorMessage: translate('entity.validation.minlength', { min: 1 }) },
                      maxLength: { value: 42, errorMessage: translate('entity.validation.maxlength', { max: 42 }) },
                      pattern: { value: '[A-Z]+', errorMessage: translate('entity.validation.pattern', { pattern: '[A-Z]+' }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeIntegerLabel" for="typeInteger">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeInteger">Type Integer</Translate>
                  </Label>
                  <AvField id="exemple-typeInteger" type="string" className="form-control" name="typeInteger" />
                </AvGroup>
                <AvGroup>
                  <Label id="typeLongLabel" for="typeLong">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeLong">Type Long</Translate>
                  </Label>
                  <AvField id="exemple-typeLong" type="string" className="form-control" name="typeLong" />
                </AvGroup>
                <AvGroup>
                  <Label id="typeBigDecimalLabel" for="typeBigDecimal">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeBigDecimal">Type Big Decimal</Translate>
                  </Label>
                  <AvField id="exemple-typeBigDecimal" type="text" name="typeBigDecimal" />
                </AvGroup>
                <AvGroup>
                  <Label id="typeLocalDateLabel" for="typeLocalDate">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeLocalDate">Type Local Date</Translate>
                  </Label>
                  <AvField id="exemple-typeLocalDate" type="date" className="form-control" name="typeLocalDate" />
                </AvGroup>
                <AvGroup>
                  <Label id="typeInstantLabel" for="typeInstant">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeInstant">Type Instant</Translate>
                  </Label>
                  <AvInput
                    id="exemple-typeInstant"
                    type="datetime-local"
                    className="form-control"
                    name="typeInstant"
                    placeholder={'YYYY-MM-DD HH:mm'}
                    value={isNew ? null : convertDateTimeFromServer(this.props.exempleEntity.typeInstant)}
                  />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="typeImageBlobLabel" for="typeImageBlob">
                      <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeImageBlob">Type Image Blob</Translate>
                    </Label>
                    <br />
                    {typeImageBlob ? (
                      <div>
                        <a onClick={openFile(typeImageBlobContentType, typeImageBlob)}>
                          <img src={`data:${typeImageBlobContentType};base64,${typeImageBlob}`} style={{ maxHeight: '100px' }} />
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {typeImageBlobContentType}, {byteSize(typeImageBlob)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('typeImageBlob')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_typeImageBlob" type="file" onChange={this.onBlobChange(true, 'typeImageBlob')} accept="image/*" />
                    <AvInput type="hidden" name="typeImageBlob" value={typeImageBlob} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <Label id="typeTextBlobLabel" for="typeTextBlob">
                    <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeTextBlob">Type Text Blob</Translate>
                  </Label>
                  <AvInput id="exemple-typeTextBlob" type="textarea" name="typeTextBlob" />
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="typeAnyBlobLabel" for="typeAnyBlob">
                      <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeAnyBlob">Type Any Blob</Translate>
                    </Label>
                    <br />
                    {typeAnyBlob ? (
                      <div>
                        <a onClick={openFile(typeAnyBlobContentType, typeAnyBlob)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {typeAnyBlobContentType}, {byteSize(typeAnyBlob)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('typeAnyBlob')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_typeAnyBlob" type="file" onChange={this.onBlobChange(false, 'typeAnyBlob')} />
                    <AvInput type="hidden" name="typeAnyBlob" value={typeAnyBlob} />
                  </AvGroup>
                </AvGroup>
                <AvGroup>
                  <AvGroup>
                    <Label id="typeBlobLabel" for="typeBlob">
                      <Translate contentKey="jhipsterSampleApplicationApp.exemple.typeBlob">Type Blob</Translate>
                    </Label>
                    <br />
                    {typeBlob ? (
                      <div>
                        <a onClick={openFile(typeBlobContentType, typeBlob)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                        <br />
                        <Row>
                          <Col md="11">
                            <span>
                              {typeBlobContentType}, {byteSize(typeBlob)}
                            </span>
                          </Col>
                          <Col md="1">
                            <Button color="danger" onClick={this.clearBlob('typeBlob')}>
                              <FontAwesomeIcon icon="times-circle" />
                            </Button>
                          </Col>
                        </Row>
                      </div>
                    ) : null}
                    <input id="file_typeBlob" type="file" onChange={this.onBlobChange(false, 'typeBlob')} />
                    <AvInput type="hidden" name="typeBlob" value={typeBlob} />
                  </AvGroup>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/exemple" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  exempleEntity: storeState.exemple.entity,
  loading: storeState.exemple.loading,
  updating: storeState.exemple.updating,
  updateSuccess: storeState.exemple.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ExempleUpdate);
