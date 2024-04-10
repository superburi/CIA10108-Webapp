package trackdao;

import java.util.List;

import trackvo.Track;

public interface TrackDao {
	
	void insert(Track rmt);

	void delete(Integer rNo, Integer memNo);

	void update(Track rmt);
	
	Track findByPK(Integer rNo, Integer memNo);
	
	List<Track> getAll();

}
