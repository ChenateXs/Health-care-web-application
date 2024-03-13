import { StringToEnumPipe } from './string-to-enum.pipe';

describe('StringToEnumPipe', () => {
  it('create an instance', () => {
    const pipe = new StringToEnumPipe();
    expect(pipe).toBeTruthy();
  });
});
