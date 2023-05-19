package booklib.Entity;

import java.util.Date;

public record BorrowResponse(Boolean bookAvailable, String borrowID, Date expectedReturnDate) {
}
