package mybatis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListUtils {

	public static <T> List<T> toList(Set<T> set) {

		if (set == null)
			return null;

		if (set.isEmpty())
			return new ArrayList<T>();

		List<T> list = new ArrayList<T>();
		Iterator<T> iterator = set.iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();
			list.add(next);
		}
		return list;
	}

}
