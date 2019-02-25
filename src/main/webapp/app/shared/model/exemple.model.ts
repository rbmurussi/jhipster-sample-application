import { Moment } from 'moment';

export interface IExemple {
  id?: string;
  typeString?: string;
  typeStringPattern?: string;
  typeInteger?: number;
  typeLong?: number;
  typeBigDecimal?: number;
  typeLocalDate?: Moment;
  typeInstant?: Moment;
  typeImageBlobContentType?: string;
  typeImageBlob?: any;
  typeTextBlob?: any;
  typeAnyBlobContentType?: string;
  typeAnyBlob?: any;
  typeBlobContentType?: string;
  typeBlob?: any;
}

export const defaultValue: Readonly<IExemple> = {};
