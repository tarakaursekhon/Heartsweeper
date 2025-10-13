       program-id. save_user_scores as "save_user_scores".

       environment division.
       configuration section.

       file-control.
       select user-scores assign "../userscores.dat"
           access is sequential
           status is file-status.

       data division.
       fd user-scores.
       01 f-score.
         03 f-attempt    pic 9(2).
         03 f-covered    pic 9(2).

       working-storage section.
       01 file-status  pic 9(2).
       01 score.
         03 attempt    pic 9(2).
         03 covered    pic 9(2).

       linkage section.
       01 attempt-in   pic 9(2).
       01 covered-in   pic 9(2).

       >>JAVA-CALLABLE
       procedure division using attempt-in covered-in.
           move attempt-in to attempt
           move covered-in to covered
           move score to f-score

           open output user-scores
           write f-score
           close user-scores

           goback.

       end program save_user_scores.
