package ibf2022.paf.day29.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.BucketOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Service;

import static ibf2022.paf.day29.Constants.*;

@Service
public class TvShowService {

    @Autowired
    private MongoTemplate template;

    public List<Document> countGenres(){

        // $unwind
        UnwindOperation unwindGenres = Aggregation.unwind(FIELD_GENRES);

        // $group
        GroupOperation groupShowsByGenres = Aggregation.group(FIELD_GENRES)
            .count().as(FIELD_TOTAL);

        // $sort
        SortOperation sortByGenres = Aggregation.sort(Sort.by(Direction.ASC, FIELD_UNDERSCORE_ID));

        Aggregation pipeline = Aggregation.newAggregation(unwindGenres, groupShowsByGenres, sortByGenres);

        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
            .getMappedResults();
    }

    public List<Document> histogramOfRatings() {

        // Create the required operation from the mongodb query

        BucketOperation ratingsBucket = Aggregation.bucket(FIELD_RATING_AVERAGE)
            .withBoundaries(3, 6, 9)
            .withDefaultBucket(">9")
            .andOutput(FIELD_NAME).push().as(FIELD_TITLES);

        // Create the pipeline to hold the operation 

        Aggregation pipeline = Aggregation.newAggregation(ratingsBucket);

        // Use the template to get back the result by passing in the pipeline, collection and class type
        
        return template.aggregate(pipeline, COLLECTION_TV, Document.class)
            .getMappedResults();
        
    }


}
