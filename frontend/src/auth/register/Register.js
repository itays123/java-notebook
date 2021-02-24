import FormInput, { classicValidate, useModel } from "../../shared/FormInput";
import { useEmailValidation } from "../useEmailValidation";
import { useRegister } from "./useRegister";

const Register = () => {
  const name = useModel('', classicValidate);
  const { validate } = useEmailValidation();
  const email = useModel('', validate);
  const passwordConfirm = useModel('', classicValidate);
  const password = useModel('', value => {
    if (value.trim() === '') return 'Password must not be empty'
    else if (value !== passwordConfirm.value) return 'Password must match';
    else return undefined;
  });
  const { register } = useRegister();
  return (
    <div className="register container mx-auto text-center">
      <h1 className="text-2xl mt-6 mb-2">Get Started</h1>
      <FormInput
        model={name}
        hint="Your Name"
        focusOnRender
        className="mx-auto"
      />
      <FormInput
        model={email}
        hint="Your Email"
        className="mx-auto"
      />
        <FormInput
        model={password}
        type="password"
        hint="Your Password"
        className="mx-auto"
        focusOnRender
        />
        <FormInput
        model={passwordConfirm}
        type="password"
        hint="Confirm Password"
        className="mx-auto"
        changeCallback={() => password.setShowError(false)}
        onKeyEnter={() => {
            if (!password.error)
            register(email.validatedValue, password.validatedValue);
            else password.setShowError(true);
        }}
        />
      <div className="mx-auto w-52">
        <button
          className="w-52 dark-button flex justify-center"
          disabled={Boolean(password.error || email.error || name.error)}
          onClick={() => {
            if (
              name.validatedValue &&
              email.validatedValue &&
              password.validatedValue
            ) {
              register(
                name.validatedValue,
                email.validatedValue,
                password.validatedValue
              );
            } 
          }}
        >
          GO
        </button>
      </div>
    </div>
  );
}
 
export default Register;