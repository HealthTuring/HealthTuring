import { AbstractControl, FormArray, FormGroup, ValidationErrors } from "@angular/forms";

function sleep() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(true);
    }, 2500)
  })
}

export class FormUtils {

  // Expresiones regulares
  static namePattern = '([a-zA-Z]+) ([a-zA-Z]+)';
  static emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$';
  static notOnlySpacesPattern = '^[a-zA-Z0-9]+$';

  static getTextError(errors: ValidationErrors) {
    for (const key of Object.keys(errors)) {
      switch (key) {
        case 'required':
          return 'Este campo es requerido';
        case 'minlength':
          return `Mínimo de ${errors[key].requiredLength} caracteres`;
        case 'min':
          return `Valor mínimo de ${errors[key].min}`;
        case 'email':
          return 'El valor ingresado no es un correo electrónico';
        case 'emailTaken':
          return 'El correo electrónico ya esta siendo usado por otro usuario';
        case 'noAdmin':
          return 'Nombre de usuario no permitido';
        case 'pattern':
          if (errors['pattern'].requiredPattern === FormUtils.emailPattern) {
            return 'Formato de correo electrónico inválido';
          }
          return 'Error de patrón contra expresión regular'
        default:
          return `Error de validación no controlado ${key}`
      }
    }
    return null;
  }

  static isValidField(form: FormGroup, fieldName: string): boolean {
    return (!!form.controls[fieldName].errors && form.controls[fieldName].touched);
  }

  static getFieldError(form: FormGroup, fieldName: string): string | null {

    if (!form.controls[fieldName]) return null;

    const errors = form.controls[fieldName].errors ?? {};

    return FormUtils.getTextError(errors);

  }

  static isValidFieldInArray(formArray: FormArray, index: number): boolean {
    return (!!formArray.controls[index].errors && formArray.controls[index].touched);
  }

  static getFieldErrorInArray(formArray: FormArray, index: number): string | null {

    if (formArray.controls.length === 0) return null;

    const errors = formArray.controls[index].errors ?? {};

    return FormUtils.getTextError(errors);

  }

  static isFieldOneEqualFieldTwo(field1: string, field2: string) {
    return (formGroup: AbstractControl) => {
      const field1Value = formGroup.get(field1)?.value;
      const field2Value = formGroup.get(field2)?.value;
      return field1Value === field2Value ? null : { passwordsNotEqual: true};
    }
  }

  static async checkingServerResponse(control: AbstractControl): Promise<ValidationErrors | null> {
    await sleep(); // 2 segundos y medio
    const formValue = control.value;

    if (formValue === 'hola@mundo.com') {
      return {
        emailTaken: true
      }
    }
    return null;
  }

  static notAdmin(control: AbstractControl): ValidationErrors | null {
    return (control.value === 'Admin') ? {noAdmin: true} : null;
  }
}
