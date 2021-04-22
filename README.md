# Java Notebook

A note application written using React.js and Tailwind.css for the UI and the Spring framework for the server.

## Frontend Architecture

- Auth components
    - Login components
        - `Login` is the form responsible for collecting login data
        - `useLogin` is the hook responsible for fetching login data
    - Register components
        - `Register` is the component responsible for collecting register data
        - `useRegister` is the hook responsible for fetching register data
    - `AuthContext` is the context storing auth data
    - `AuthOnly` tracks auth state and only renders its children if the user is authenticated.
    - `useEmailValidation` is the hook checking for the existence of an email
- Text Block components
    - Block Edit
        - `BlockEdit` is the component responsible for editing a specific block
        - `BlockTypePicker` edits the block type
        - `useBlockEditor` is the hook responsible for tracking changes in blocks
    - Block view
        - `Block` is the non-editable block view
    - `BlockList` displays a list of editable and non-editable blocks
- Note components
    - Note Creating
        - `CreateNote` is the note creation UI.
        - `useCreateNote` fetches the new note route.
    - Note Editing
        - `NoteEditorContext` holds the edited note data
        - `NoteTitleEdit` edits the title of the note
        - `useDeleteNote` fetches the note removal route
        - `useSaveNote` fetches the update note route
    - Note Viewing
        - `NoteTitle` displays the title
        - `NoteView` displays the note title and the list of its blocks
    - `useNote` fetches the note data
- Shared components
    - http
        - `useFetch` fetches the data on a given route
    - Navigation
        - `Navbar` displays a list of links
        - `NavigationWrapper` sets the `Navbar` position on the screen
        - `NavItem` is the individual link component.
        - `SignedInLinks` is the list of links for signed-in users
        - `SignedOutLinks` is the list of links for unauthenticated users.
        - `useLogout` fetches logout data.
    - NoteList
        - `NoteItem` is a summary of the note data
        - `NoteList` is a list of `NoteItem`s.
    - `FormInput` is a simple text form.
    - `Home` is the main route component that displays a list of notes for signed-in users.
    - `Welcome` is the main route for unauthenticated users
- `App` is the main router.

## Backend Architecture

- Authentication
    - `JWTFilter` is a spring filter for getting JWTs from cookies
- Block
    - `Block` is the block model
    - `BlockType` is an enum for types of blocks
- Note
    - `Note` is the note model containing a list of blocks
    - `NoteController` is responsible for handling note-related requests
    - `NoteRepository` is the JPA repo for querying notes
    - `NoteService` handles authentication contexts and queries notes
    - `SecureNote` is the note returned to the user
    - `UpdateableNote` is a note received by the user.
- User
    - `SecureUser` s the user sent to the front inside a `SecureNote`, not containing the password
    - `User` is the user entity.
    - `UserController` handles user-related requests.
    - `UserProfile` is the user returned to the front when asking for the profile.
    - `UserRepository` queries user-related queries
    - `UserService` handles queries and business logic.
- `SecurityConfig` is responsible for the security configurations.
