package Counter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Counter {
	private int count;
	private int actScore;
	private int maxScore;
	private LocalDate lastCount;
	public Counter(int count, int actScore, int maxScore, String lastCount) {
		this.count = count;
		this.actScore = actScore;
		this.maxScore = maxScore;
		if(lastCount == "") {
			this.lastCount = LocalDate.now();
		}
		else {
			this.lastCount = LocalDate.parse(lastCount);
		}
		
	}
	//alle brutte l'ultimo update è oggi di default
	public Counter(int count, int actScore, int maxScore) {
		this.count = count;
		this.actScore = actScore;
		this.maxScore = maxScore;
		this.lastCount = LocalDate.now();
	}
	public Counter() {
		this.count = 0;
		this.actScore = 0;
		this.maxScore = 0;
		this.lastCount = LocalDate.now();
	}
	public void incCount() {
		this.count += 1;
		incScore();
	}
	public void decCount() {
		this.count -= 1;
	}
	public int getCount() {
		return count;
	}
	private void incScore() {
		if(lastCount == null) {
			lastCount = LocalDate.now();
		}
		if(lastCount.equals(LocalDate.now())){
			actScore += 1;
			if(actScore >= maxScore) {
				maxScore = actScore;
			}
		}
	}
	public int getActScore() {
		if(lastCount == null) {
			lastCount = LocalDate.now();
		}
		if(!lastCount.equals(LocalDate.now())) {
			actScore = 0;
		}
		return actScore;
	}
	public int getMaxScore() {
		return maxScore;
	}
	
	public String getLastCount() {
		return lastCount.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
		
	}
	
	public String toString() {
		return count + ":" + actScore + ":" + maxScore + ":" + getLastCount();
	}
}
