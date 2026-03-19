import java.nio.file.Path;

record User(String id, String name, String email) {}

interface UserRepository {
    void save(User user);
}

interface FileService{
    void writeToFile(Path backupPath, String string);
}

class UserService {

    private static final String BACKUP_DIRECTORY = "/backup/users/";
    private static final String BACKUP_FILE_EXTENSION = ".txt";

    private final UserRepository userRepository;
    private final FileService fileService;

    public UserService(UserRepository userRepository, FileService fileService) {
        this.userRepository = userRepository;
        this.fileService = fileService;
    }

    public void saveUser(User user){
        validateUser(user);
        userRepository.save(user);
        backupUser(user);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (isInvalid(user.name()) || isInvalid(user.email())) {
            throw new IllegalArgumentException("Invalid user data: Name and Email are required.");
        }
    }
    private boolean isInvalid(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void backupUser(User user) {
        String fileName = user.id() + BACKUP_FILE_EXTENSION;
        Path backupPath = Path.of(BACKUP_DIRECTORY, fileName);

        fileService.writeToFile(backupPath, user.toString());
    }

}


