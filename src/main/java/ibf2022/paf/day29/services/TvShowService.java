package ibf2022.paf.day29.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Service;

@Service
public class TvShowService {

    @Autowired
    private MongoTemplate template;

    public List<Document> countGenres(){
        // $unwind
        UnwindOperation unwindGenres = Aggregation.unwind(FIELD_GENRES);

        // $group
        GroupOperation groupShowsByGenres = Aggregation.group(FIELD_GENRES);

        // $sort
        SortOperation sortByGenres = Aggregation.sort(Sort.by(Direction.ASC, FIELD_UNDERSCORE_ID));
    }

    public List<Document> histogramOfRatings() {
        
    }


}
