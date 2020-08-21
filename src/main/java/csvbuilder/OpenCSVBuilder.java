package csvbuilder;

import csvbuilder.ICSVBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csvbuilder.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {

    @Override
    public Iterator<E> getCSVFileIterator (Reader reader, Class csvClass)throws CSVBuilderException {
        try{
            return this.getCSVBuild(reader, csvClass).iterator();
        }catch (IllegalStateException e){
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID);
        }
    }

    @Override
    public List<E> getCSVFileList (Reader reader, Class csvClass)throws CSVBuilderException{
        try{
            return this.getCSVBuild(reader, csvClass).parse();
        }catch (IllegalStateException e){
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),
                    CSVBuilderException.ExceptionType.NOT_A_CSV_TYPE_OR_HEADERS_INVALID);
        }
    }

    private CsvToBean<E> getCSVBuild(Reader reader, Class csvClass) {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
    }
}
