import FormInput, { classicValidate, useModel } from "../../shared/FormInput";
import { useEmailValidation } from "../useEmailValidation";
import { useLogin } from "./useLogin";


const Login = () => {
      const { validate } = useEmailValidation();
      const email = useModel('', validate);
      const password = useModel('', classicValidate);
      const { login } = useLogin();
      return (
        <div className="login container mx-auto text-center py-2">
          <h1 className="text-2xl mt-6 mb-2">Log in</h1>
          <FormInput
            model={email}
            hint="Your Email"
            className="mx-auto"
            focusOnRender
          />
        <FormInput
            model={password}
            type="password"
            hint="Your Password"
            className="mx-auto"
            onKeyEnter={() => {
            if (!password.error)
                login(email.validatedValue, password.validatedValue);
            else password.setShowError(true);
            }}
        />
          <div className="mx-auto w-52">
            <button
              className="w-52 dark-button flex justify-center"
              disabled={Boolean(password.error || email.error)}
              onClick={() => {
                if (
                  email.validatedValue &&
                  password.validatedValue
                ) {
                  login(email.validatedValue, password.validatedValue);
                } 
              }}
            >
              GO
            </button>
          </div>
        </div>
      );
}
 
export default Login;